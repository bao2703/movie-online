using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class CommentController : BaseController
    {
        private readonly NeptuneContext _context;
        private readonly IMapper _mapper;

        public CommentController(NeptuneContext context, IMapper mapper)
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