using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Domains;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Extensions;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]")]
    public class AuthController : BaseController
    {
        private readonly NeptuneContext _context;
        private readonly IMapper _mapper;

        public AuthController(NeptuneContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register([FromBody] RegisterRequest model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ErrorReponse.InvalidPayload);
            }

            var user = _mapper.Map<User>(model);
            user.Email = user.Email.ToLower();

            if (_context.Users.IsExistEmail(user.Email))
            {
                return BadRequest(ErrorReponse.EmailConflict);
            }

            await _context.Users.AddAsync(user);
            await _context.SaveChangesAsync();

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

            if (!_context.Users.IsExistEmail(model.Email))
            {
                return BadRequest(ErrorReponse.EmailNotFound);
            }

            if (!_context.Users.VerifyUser(model.Email, model.Password))
            {
                return BadRequest(ErrorReponse.InvalidLogin);
            }

            return Ok();
        }
    }
}