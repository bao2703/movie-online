using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using MovieOnline.Data.Seeds;

namespace MovieOnline.Controllers
{
    [Route("api/[controller]")]
    [Produces("application/json")]
    public class SeedController : Controller
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
            return Ok();
        }
    }
}