using System.Collections.Generic;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class MovieController : BaseController
    {
        private readonly IMapper _mapper;
        private readonly IMovieRepository _movieRepository;
        private readonly IUnitOfWork _unitOfWork;

        public MovieController(IMapper mapper, IUnitOfWork unitOfWork, IMovieRepository movieRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _movieRepository = movieRepository;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var movies = _movieRepository.ToList();
            var reponses = _mapper.Map<List<MovieReponse>>(movies);
            return Ok(reponses);
        }

        [HttpGet("{id}")]
        public IActionResult Index(int id)
        {
            var movie = _movieRepository.FindById(id);
            var reponse = _mapper.Map<MovieReponse>(movie);
            return Ok(reponse);
        }
    }
}