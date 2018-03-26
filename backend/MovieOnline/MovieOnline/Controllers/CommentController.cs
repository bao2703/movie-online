using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class CommentController : BaseController
    {
        private readonly ICommentRepository _commentRepository;
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;

        public CommentController(IMapper mapper, IUnitOfWork unitOfWork, ICommentRepository commentRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _commentRepository = commentRepository;
        }

        [HttpGet]
        public IActionResult Index()
        {
            return Ok();
        }
    }
}