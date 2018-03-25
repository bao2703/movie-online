using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Models.Reponses;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class MovieController : BaseController
    {
        private readonly NeptuneContext _context;
        private readonly IMapper _mapper;

        public MovieController(NeptuneContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var movies = _context.Movies.ToList();
            var reponses = _mapper.Map<List<MovieReponse>>(movies);
            return Ok(reponses);
        }
    }
}