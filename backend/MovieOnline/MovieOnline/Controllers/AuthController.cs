using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Entities;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;

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
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            var user = _mapper.Map<UserEntity>(model);
            user.Email = user.Email.ToLower();

            if (_userRepository.IsExistEmail(user.Email))
            {
                return BadRequest(ErrorReponse.EmailConflict);
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
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            model.Email = model.Email.ToLower();

            if (!_userRepository.VerifyUser(model.Email, model.Password))
            {
                return BadRequest(ErrorReponse.InvalidLogin);
            }

            return Ok();
        }
    }
}