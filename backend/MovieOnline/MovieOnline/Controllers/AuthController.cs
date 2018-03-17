using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Domains;
using MovieOnline.Data.Errors;
using MovieOnline.Data.Models;
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
        public async Task<IActionResult> Register([FromBody] RegisterModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(Error.InvalidPayload);
            }

            var user = _mapper.Map<User>(model);
            user.Email = user.Email.ToLower();

            if (_context.Users.IsExistEmail(user.Email))
            {
                return BadRequest(Error.EmailConflict);
            }

            await _context.Users.AddAsync(user);
            await _context.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("login")]
        public IActionResult Login([FromBody] LoginModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(Error.InvalidPayload);
            }

            model.Email = model.Email.ToLower();

            if (!_context.Users.IsExistEmail(model.Email))
            {
                return BadRequest(Error.EmailNotFound);
            }

            if (!_context.Users.VerifyUser(model.Email, model.Password))
            {
                return BadRequest(Error.InvalidLogin);
            }

            return Ok();
        }
    }
}