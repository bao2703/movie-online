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
                o.AvatarUrl = f.Internet.Avatar();
                o.Email = f.Person.Email.ToLower();
                o.Password = "1";
                o.Role = f.PickRandom(o.Role);
            });

            var admins = userFaker.Generate(25);
            for (var i = 0; i < admins.Count; i++)
            {
                admins[i].Role = Role.Administrator;
                admins[i].Name = $"admin{i}";
                admins[i].Email = $"admin{i}@gmail.com";
            }

            var users = userFaker.Generate(50);
            for (var i = 0; i < users.Count; i++)
            {
                users[i].Role = Role.None;
                users[i].Name = $"user{i}";
                users[i].Email = $"user{i}gmail.com";
            }

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
                o.Description = f.Lorem.Sentences(f.Random.Number(5, 10));
                o.Views = f.Random.Number(1000000);
                o.Rating = f.Random.Float(1, 5);
                o.PosterUrl = f.Image.Image();

                o.GenreMovies = new List<GenreMovieEntity>();
                var randomGenres = f.PickRandom(genres, f.Random.Number(2, 5)).ToList();
                randomGenres.ForEach(g => o.GenreMovies.Add(new GenreMovieEntity { Genre = g }));

                o.Episodes = new List<EpisodeEntity>();
                for (int i = 1; i < f.Random.Number(5, 15); i++)
                {
                    o.Episodes.Add(new EpisodeEntity()
                    {
                        Name = $"Ep {i}"
                    });
                }
            });

            var movies = movieFaker.Generate(100);
            _context.Movies.AddRange(movies);

            var commentFaker = new Faker<CommentEntity>().Rules((f, o) =>
            {
                o.Content = f.Lorem.Sentences(f.Random.Number(1, 4));
                o.DateCreated = f.Date.Past();
                o.UserId = f.PickRandom(users).Id;
                o.MovieId = f.PickRandom(movies).Id;
            });

            var comments = commentFaker.Generate(50);
            _context.Comments.AddRange(comments);

            await _context.SaveChangesAsync();
        }
    }
}