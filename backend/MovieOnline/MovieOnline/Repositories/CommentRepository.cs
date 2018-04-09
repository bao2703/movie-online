using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface ICommentRepository : IRepository<CommentEntity>
    {
    }

    public class CommentRepository : Repository<CommentEntity>, ICommentRepository
    {
        public CommentRepository(NeptuneContext context) : base(context)
        {
        }
    }
}