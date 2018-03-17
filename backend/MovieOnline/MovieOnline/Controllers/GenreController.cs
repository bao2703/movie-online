using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;

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
            return Ok();
        }
    }
}