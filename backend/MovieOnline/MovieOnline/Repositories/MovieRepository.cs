using System.Collections.Generic;
using System.Linq;
using Microsoft.EntityFrameworkCore;
using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IMovieRepository : IRepository<MovieEntity>
    {
        List<CommentEntity> FindComments(int id);
    }

    public class MovieRepository : Repository<MovieEntity>, IMovieRepository
    {
        public MovieRepository(NeptuneContext context) : base(context)
        {
        }

        public List<CommentEntity> FindComments(int id)
        {
            var movie = DbSet.Include(m => m.Comments).SingleOrDefault(m => m.Id == id);
            if (movie == null) return null;
			return movie.Comments.ToList();
        }
  }
}