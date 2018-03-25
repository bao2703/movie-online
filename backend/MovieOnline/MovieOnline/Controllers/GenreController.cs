using System.Collections.Generic;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class GenreController : BaseController
    {
        private readonly IGenreRepository _genreRepository;
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;

        public GenreController(IMapper mapper, IUnitOfWork unitOfWork, IGenreRepository genreRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _genreRepository = genreRepository;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var genres = _genreRepository.ToList();
            var reponses = _mapper.Map<List<GenreReponse>>(genres);
            return Ok(reponses);
        }
    }
}