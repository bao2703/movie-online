﻿using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Seeders;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]s")]
    public class SeedController : BaseController
    {
        private readonly Seeder _seeder;

        public SeedController(Seeder seeder)
        {
            _seeder = seeder;
        }

        [HttpGet]
        public async Task<IActionResult> Init()
        {
            await _seeder.InitializeAsync();
            return Ok("done");
        }
    }
}