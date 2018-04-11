using System.Collections.Generic;
using System.Linq;
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
        private readonly IUnitOfWork _unitOfWork;
        private readonly IMovieRepository _movieRepository;

        public MovieController(IMapper mapper, IUnitOfWork unitOfWork, IMovieRepository movieRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _movieRepository = movieRepository;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var movies = _movieRepository.OrderBy(m => m.Name).ToList();
            var reponses = _mapper.Map<List<MovieReponse>>(movies);
            return Ok(reponses);
        }

        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var movie = _movieRepository.FindById(id);
            var reponse = _mapper.Map<MovieReponse>(movie);
            return Ok(reponse);
        }

        [HttpGet("comments/{id}")]
        public IActionResult GetComments(int id)
        {
            var comments = _movieRepository.FindCommentsById(id).ToList();
            var reponses = _mapper.Map<List<CommentReponse>>(comments);
            return Ok(reponses);
        }
    }
}