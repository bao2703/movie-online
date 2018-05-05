using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;
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
            var comments = _commentRepository.OrderByDescending(c => c.DateCreated).ToList();
            var reponses = _mapper.Map<List<CommentResponse>>(comments);
            return Ok(reponses);
        }

        [HttpPost]
        public async Task<IActionResult> Create([FromBody] CommentRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            var comment = _mapper.Map<CommentEntity>(model);
            comment.DateCreated = DateTime.Today;

            await _commentRepository.AddAsync(comment);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }
    }
}