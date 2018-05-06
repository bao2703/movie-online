using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;
using Microsoft.AspNetCore.Http;
using System.IO;
using System;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class MovieController : BaseController
    {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly IMovieRepository _movieRepository;

        public MovieController(IMapper mapper, IUnitOfWork unitOfWork, IMovieRepository movieRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _movieRepository = movieRepository;
        }

        [HttpGet]
        public IActionResult GetAll(string searchString)
        {
            var movies = _movieRepository.OrderBy(m => m.Name);

            if (!string.IsNullOrEmpty(searchString))
            {
                movies = movies.Where(m => m.Name.ToLower().Contains(searchString.ToLower()));
            }

            var reponses = _mapper.Map<List<MovieResponse>>(movies.ToList());
            return Ok(reponses);
        }

        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var movie = _movieRepository.FindById(id);
            var reponse = _mapper.Map<MovieResponse>(movie);
            return Ok(reponse);
        }

        [HttpGet("comments/{id}")]
        public IActionResult GetComments(int id)
        {
            var comments = _movieRepository.FindCommentsById(id).ToList();
            var reponses = _mapper.Map<List<CommentResponse>>(comments);
            return Ok(reponses);
        }

        [HttpGet("episodes/{id}")]
        public IActionResult GetEpisodes(int id)
        {
            var comments = _movieRepository.FindEpisodesById(id).ToList();
            var reponses = _mapper.Map<List<EpisodeResponse>>(comments);
            return Ok(reponses);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] MovieRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            var movie = _mapper.Map<MovieEntity>(model);

            await _movieRepository.AddAsync(movie);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Edit(int id, [FromBody] MovieRequest model)
        {
            var movie = _movieRepository.FindById(id);

            if (!ModelState.IsValid || movie == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            movie.Name = model.Name;
            movie.Description = model.Description;
            movie.PosterUrl = model.PosterUrl;

            _movieRepository.Update(movie);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var movie = _movieRepository.FindById(id);

            if (movie == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            _movieRepository.Remove(movie);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("increase/{id}")]
        public async Task<IActionResult> IncreaseView(int id)
        {
            var movie = _movieRepository.FindById(id);

            if (movie == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            movie.Views += 1;
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("upload")]
        public async Task<IActionResult> Upload([FromForm] IFormFile file)
        {
            var filePath = $"/{DateTime.Now.ToFileTime()}_{file.FileName}";
            using (var stream = new FileStream($"wwwroot/{filePath}", FileMode.Create))
            {
                await file.CopyToAsync(stream);
            }
            return Ok(filePath);
        }
    }
}