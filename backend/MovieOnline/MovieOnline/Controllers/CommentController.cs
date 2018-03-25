using AutoMapper;
using Microsoft.AspNetCore.Mvc;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class CommentController : BaseController
    {
        private readonly IMapper _mapper;

        public CommentController(IMapper mapper)
        {
            _mapper = mapper;
        }

        [HttpGet]
        public IActionResult Index()
        {
            return Ok();
        }
    }
}