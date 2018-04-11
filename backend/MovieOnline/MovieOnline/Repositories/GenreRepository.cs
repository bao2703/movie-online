using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IGenreRepository : IRepository<GenreEntity>
    {
        IEnumerable<MovieEntity> FindMoviesById(int id);
    }

    public class GenreRepository : Repository<GenreEntity>, IGenreRepository
    {
        public GenreRepository(NeptuneContext context) : base(context)
        {
        }

        public IEnumerable<MovieEntity> FindMoviesById(int id)
        {
            var genre = DbSet.Include(g => g.GenreMovies)
                            .ThenInclude(g => g.Movie)
                            .SingleOrDefault(m => m.Id == id);
            if (genre == null)
            {
                return null;
            }
            return genre.GenreMovies.Select(gm => gm.Movie).OrderBy(m => m.Name);
        }
  }
}