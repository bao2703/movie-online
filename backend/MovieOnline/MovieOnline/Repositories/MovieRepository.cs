using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IMovieRepository : IRepository<MovieEntity>
    {
    }

    public class MovieRepository : Repository<MovieEntity>, IMovieRepository
    {
        public MovieRepository(NeptuneContext context) : base(context)
        {
        }
    }
}