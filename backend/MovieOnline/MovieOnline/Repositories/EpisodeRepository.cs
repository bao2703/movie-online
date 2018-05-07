using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IEpisodeRepository : IRepository<EpisodeEntity>
    {
    }

    public class EpisodeRepository : Repository<EpisodeEntity>, IEpisodeRepository
    {
        public EpisodeRepository(NeptuneContext context) : base(context)
        {
        }
    }
}