using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Models.Reponses;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class GenreController : BaseController
    {
        private readonly NeptuneContext _context;
        private readonly IMapper _mapper;

        public GenreController(NeptuneContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var genres = _context.Genres.ToList();
            var reponses = _mapper.Map<List<GenreReponse>>(genres);
            return Ok(reponses);
        }
    }
}