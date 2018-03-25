using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IGenreRepository : IRepository<GenreEntity>
    {
    }

    public class GenreRepository : Repository<GenreEntity>, IGenreRepository
    {
        public GenreRepository(NeptuneContext context) : base(context)
        {
        }
    }
}