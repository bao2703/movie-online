using System.Collections.Generic;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Reponses;
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
            var comments = _commentRepository.ToList();
            var reponses = _mapper.Map<List<CommentReponse>>(comments);
            return Ok(reponses);
        }
    }
}