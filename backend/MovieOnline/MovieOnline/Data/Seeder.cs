using System.Linq;
using System.Threading.Tasks;
using Bogus;
using MovieOnline.Data.Domains;

namespace MovieOnline.Data
{
    public class Seeder
    {
        private readonly NeptuneContext _context;

        public Seeder(NeptuneContext context)
        {
            _context = context;
        }

        public async Task InitializeAsync()
        {
            _context.Database.EnsureDeleted();
            _context.Database.EnsureCreated();

            var userFaker = new Faker<User>().Rules((f, o) =>
            {
                o.Name = f.Name.FindName();
                o.Phone = f.Person.Phone;
                o.Avatar = f.Internet.Avatar();
                o.Email = f.Person.Email.ToLower();
                o.Password = "1";
                o.Role = f.PickRandom(o.Role);
            });

            var index = 1;
            var admins = userFaker.Generate(25);
            admins.ToList().ForEach(x =>
            {
                x.Role = Role.Administrator;
                x.Name = $"admin{index}";
                x.Email = $"admin{index++}@gmail.com";
            });

            index = 1;
            var users = userFaker.Generate(50);
            users.ToList().ForEach(x =>
            {
                x.Role = Role.None;
                x.Name = $"user{index}";
                x.Email = $"user{index++}@gmail.com";
            });

            _context.Users.AddRange(users);
            _context.Users.AddRange(admins);

            await _context.SaveChangesAsync();
        }
    }
}