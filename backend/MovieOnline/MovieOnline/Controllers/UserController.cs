using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Dtos;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class UserController : BaseController
    {
        private readonly NeptuneContext _context;
        private readonly IMapper _mapper;

        public UserController(NeptuneContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var users = _context.Users.ToList();
            var dtos = _mapper.Map<List<UserDto>>(users);
            return Ok(dtos);
        }

        [HttpGet("{id}")]
        public IActionResult Index(int id)
        {
            var user = _context.Users.Find(id);
            var dto = _mapper.Map<UserDto>(user);
            return Ok(dto);
        }
    }
}