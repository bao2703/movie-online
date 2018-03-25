using MovieOnline.Data;
using MovieOnline.Data.Entities;

namespace MovieOnline.Repositories
{
    public interface IUserRepository : IRepository<UserEntity>
    {
        UserEntity FindByEmail(string email);
        bool IsExistEmail(string email);
        bool VerifyUser(string email, string password);
    }

    public class UserRepository : Repository<UserEntity>, IUserRepository
    {
        public UserRepository(NeptuneContext context) : base(context)
        {
        }

        public UserEntity FindByEmail(string email)
        {
            return SingleOrDefault(x => x.Email == email);
        }

        public bool IsExistEmail(string email)
        {
            return Any(x => x.Email == email);
        }

        public bool VerifyUser(string email, string password)
        {
            return Any(x => x.Email == email && x.Password == password);
        }
    }
}