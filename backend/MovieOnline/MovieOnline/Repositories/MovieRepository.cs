using MovieOnline.Data;
using MovieOnline.Data.Domains;

namespace MovieOnline.Repositories
{
    public interface IMovieRepository : IRepository<Movie>
    {
    }

    public class MovieRepository : Repository<Movie>, IMovieRepository
    {
        public MovieRepository(NeptuneContext context) : base(context)
        {
        }
    }
}