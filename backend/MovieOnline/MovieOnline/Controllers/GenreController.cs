using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Reponses;
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
            var reponses = _mapper.Map<List<GenreReponse>>(genres);
            return Ok(reponses);
        }

        [HttpGet("movies/{id}")]
        public IActionResult GetMovies(int id)
        {
            var genres = _genreRepository.FindMoviesById(id).ToList();
            var reponses = _mapper.Map<List<MovieReponse>>(genres);
            return Ok(reponses);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] GenreRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            var genre = _mapper.Map<GenreEntity>(model);

            await _genreRepository.AddAsync(genre);
            await _unitOfWork.SaveChangesAsync();
 
            return Ok();
        }

        [HttpPut]
        public async Task<IActionResult> Edit([FromBody] GenreRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            var genre = _mapper.Map<GenreEntity>(model);

            _genreRepository.Update(genre);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpDelete]
        public async Task<IActionResult> Delete([FromBody] GenreRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            var genre = _mapper.Map<GenreEntity>(model);

            _genreRepository.Remove(genre);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }
    }
}