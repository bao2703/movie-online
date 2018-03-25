using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Bogus;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Seeders
{
    public class Seeder
    {
        private readonly NeptuneContext _context;
        private readonly Faker _faker = new Faker();

        private readonly List<string> _genreNames = new List<string>
        {
            "Action",
            "Adventure",
            "Animation",
            "Biography",
            "Comedy",
            "Crime",
            "Documentary",
            "Drama",
            "Family",
            "Fantasy",
            "Film Noir",
            "History",
            "Horror",
            "Music",
            "Musical",
            "Mystery",
            "Romance",
            "Sci-Fi",
            "Short",
            "Sport",
            "Superhero",
            "Thriller",
            "War",
            "Western"
        };

        public Seeder(NeptuneContext context)
        {
            _context = context;
        }

        public async Task InitializeAsync()
        {
            _context.Database.EnsureDeleted();
            _context.Database.EnsureCreated();

            var userFaker = new Faker<UserEntity>().Rules((f, o) =>
            {
                o.Name = f.Name.FindName();
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

            var genres = new List<GenreEntity>();
            foreach (var genreName in _genreNames)
            {
                genres.Add(new GenreEntity
                {
                    Name = genreName,
                    Description = _faker.Lorem.Sentence()
                });
            }

            _context.Genres.AddRange(genres);
            await _context.SaveChangesAsync();

            var movieFaker = new Faker<MovieEntity>().Rules((f, o) =>
            {
                o.Name = f.Commerce.Product();
                o.Release = f.Date.Past(5);
                o.Description = f.Lorem.Sentence();
                o.Views = f.Random.Number(1000000);
                o.Rating = f.Random.Float(1, 5);
                o.Poster = f.Image.Image(640, 480, true);

                o.GenreMovies = new List<GenreMovieEntity>();
                var randomGenres = f.PickRandom(genres, f.Random.Number(2, 5)).ToList();
                randomGenres.ForEach(g => o.GenreMovies.Add(new GenreMovieEntity { Genre = g }));
            });

            var movies = movieFaker.Generate(100);
            _context.Movies.AddRange(movies);

            await _context.SaveChangesAsync();
        }
    }
}