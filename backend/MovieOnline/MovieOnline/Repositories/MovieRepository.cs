using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IMovieRepository : IRepository<MovieEntity>
    {
        IEnumerable<CommentEntity> FindCommentsById(int id);
        IEnumerable<EpisodeEntity> FindEpisodesById(int id);
    }

    public class MovieRepository : Repository<MovieEntity>, IMovieRepository
    {
        public MovieRepository(NeptuneContext context) : base(context)
        {
        }

        public new MovieEntity FindById(object id)
        {
            return DbSet.Include(m => m.GenreMovies).ThenInclude(gm => gm.Genre).SingleOrDefault(m => m.Id == (int) id);
        }

        public IEnumerable<CommentEntity> FindCommentsById(int id)
        {
            var movie = DbSet.Include(m => m.Comments).ThenInclude(m => m.User).SingleOrDefault(m => m.Id == id);
            if (movie == null)
            {
                return null;
            }
            return movie.Comments.OrderByDescending(c => c.DateCreated);
        }

        public IEnumerable<EpisodeEntity> FindEpisodesById(int id)
        {
            var movie = DbSet.Include(m => m.Episodes).SingleOrDefault(m => m.Id == id);
            if (movie == null)
            {
                return null;
            }
            return movie.Episodes.OrderByDescending(e => e.Name.Length).ThenByDescending(e => e.Name);
        }
    }
}