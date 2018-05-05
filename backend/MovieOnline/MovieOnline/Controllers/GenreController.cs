using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class GenreController : BaseController
    {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly IGenreRepository _genreRepository;

        public GenreController(IMapper mapper, IUnitOfWork unitOfWork, IGenreRepository genreRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _genreRepository = genreRepository;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var genres = _genreRepository.OrderBy(g => g.Name).ToList();
            var reponses = _mapper.Map<List<GenreResponse>>(genres);
            return Ok(reponses);
        }

        [HttpGet("movies/{id}")]
        public IActionResult GetMovies(int id)
        {
            var genres = _genreRepository.FindMoviesById(id).ToList();
            var reponses = _mapper.Map<List<MovieResponse>>(genres);
            return Ok(reponses);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] GenreRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            var genre = _mapper.Map<GenreEntity>(model);

            await _genreRepository.AddAsync(genre);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Edit(int id, [FromBody] GenreRequest model)
        {
            var genre = _genreRepository.FindById(id);

            if (!ModelState.IsValid || genre == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            genre.Name = model.Name;
            genre.Description = model.Description;

            _genreRepository.Update(genre);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var genre = _genreRepository.FindById(id);

            if (genre == null)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            _genreRepository.Remove(genre);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }
    }
}