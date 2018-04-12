using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class CommentController : BaseController
    {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly ICommentRepository _commentRepository;

        public CommentController(IMapper mapper, IUnitOfWork unitOfWork, ICommentRepository commentRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _commentRepository = commentRepository;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var comments = _commentRepository.OrderBy(c => c.DateCreated).ToList();
            var reponses = _mapper.Map<List<CommentReponse>>(comments);
            return Ok(reponses);
        }
    }
}