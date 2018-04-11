using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Reponses;
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
    }
}