using System;
using System.IO;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
  [Route("api/[controller]s")]
  public class EpisodeController : BaseController
  {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly IEpisodeRepository _episodeRepository;

        public EpisodeController(IMapper mapper, IUnitOfWork unitOfWork, IEpisodeRepository episodeRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _episodeRepository = episodeRepository;
        }

        [HttpPut("{id}")]
        [DisableRequestSizeLimit]
        public async Task<IActionResult> Edit(int id, [FromForm] EpisodeRequest model)
        {
            var episode = _episodeRepository.FindById(id);

            if (!ModelState.IsValid || episode == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            episode.Name = model.Name;

            if (model.File != null)
            {
                var filePath = $"/{DateTime.Now.ToFileTime()}_{model.File.FileName}";
                using (var stream = new FileStream($"wwwroot/{filePath}", FileMode.Create))
                {
                    var oldPath = $"wwwroot{episode.Url}";
                    if (System.IO.File.Exists(oldPath)) System.IO.File.Delete(oldPath);

                    await model.File.CopyToAsync(stream);
                    episode.Url = filePath;
                }
            }

            _episodeRepository.Update(episode);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var episode = _episodeRepository.FindById(id);

            if (episode == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            _episodeRepository.Remove(episode);
            await _unitOfWork.SaveChangesAsync();

            if (System.IO.File.Exists(episode.Url)) System.IO.File.Delete(episode.Url);

            return Ok();
        }
  }
}