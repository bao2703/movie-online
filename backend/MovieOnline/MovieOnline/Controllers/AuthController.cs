using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Domains;
using MovieOnline.Extensions;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]")]
    [Produces("application/json")]
    public class AuthController : Controller
    {
        private readonly NeptuneContext _context;

        public AuthController(NeptuneContext context)
        {
            _context = context;
        }

        [HttpPost("register")]
        public async Task<IActionResult> RegisterAsync([FromBody] User user)
        {
            if (user == null) return BadRequest();

            user.Email = user.Email.ToLower().Trim();
            user.Password = user.Password.Trim();
            user.Role = Role.None;

            if (_context.Users.IsExistEmail(user.Email)) return BadRequest();
            
            await _context.Users.AddAsync(user);
            await _context.SaveChangesAsync();

            return Ok();
        }

        [HttpPost("signin")]
        public IActionResult SignIn([FromBody] User user)
        {
            if (user == null) return BadRequest();
            return Ok();
        }

        [HttpGet("test")]
        public IActionResult Test()
        {
            return Ok(_context.Users.FirstOrDefault());
        }
    }
}