using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data;
using MovieOnline.Data.Models.Reponses;

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
            var reponses = _mapper.Map<List<UserReponse>>(users);
            return Ok(reponses);
        }

        [HttpGet("{id}")]
        public IActionResult Index(int id)
        {
            var user = _context.Users.Find(id);
            var reponse = _mapper.Map<UserReponse>(user);
            return Ok(reponse);
        }
    }
}