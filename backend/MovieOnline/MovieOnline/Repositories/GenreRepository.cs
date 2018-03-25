using MovieOnline.Data;
using MovieOnline.Data.Domains;

namespace MovieOnline.Repositories
{
    public interface IGenreRepository : IRepository<Genre>
    {
    }

    public class GenreRepository : Repository<Genre>, IGenreRepository
    {
        public GenreRepository(NeptuneContext context) : base(context)
        {
        }
    }
}