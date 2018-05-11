using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Common;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;
using System;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]")]
    public class AuthController : BaseController
    {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly IUserRepository _userRepository;

        public AuthController(IMapper mapper, IUnitOfWork unitOfWork, IUserRepository userRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _userRepository = userRepository;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register([FromBody] RegisterRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            var user = _mapper.Map<UserEntity>(model);
            user.Email = user.Email.ToLower();
            user.DateCreated = DateTime.Now;

            if (_userRepository.IsExistEmail(user.Email))
            {
                return BadRequest(ErrorResponse.EmailConflict);
            }

            await _userRepository.AddAsync(user);
            await _unitOfWork.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("login")]
        public IActionResult Login([FromBody] LoginRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorResponse.InvalidPayload);
            }

            model.Email = model.Email.ToLower();

            var user = _userRepository.FindByEmail(model.Email);

            if (user == null || user.Password != model.Password)
            {
                return BadRequest(ErrorResponse.InvalidLogin);
            }

            var jwt = Helper.GenerateJwt(user);

            return Ok(jwt);
        }
    }
}