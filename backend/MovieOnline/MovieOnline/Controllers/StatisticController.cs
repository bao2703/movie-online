using System;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Repositories;

namespace MovieOnline.Controllers
{
  [Route("api/[controller]s")]
  public class StatisticController : BaseController
  {
        private readonly IMapper _mapper;
        private readonly IUnitOfWork _unitOfWork;
        private readonly IUserRepository _userRepository;

        public StatisticController(IMapper mapper, IUnitOfWork unitOfWork, IUserRepository userRepository)
        {
            _mapper = mapper;
            _unitOfWork = unitOfWork;
            _userRepository = userRepository;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var users = _userRepository.FindAll();
            var data = users.GroupBy(x => new DateTime(x.DateCreated.Year, x.DateCreated.Month, 1))
                        .Select(x => new { x.Key, Value = x.Count() }).OrderByDescending(x => x.Key).Take(10)
                        .Select(x => new { Key = x.Key.ToString("Y"), Value = x.Value });
            return Ok(data);
        }
  }
}