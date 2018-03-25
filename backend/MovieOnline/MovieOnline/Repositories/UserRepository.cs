using MovieOnline.Data;
using MovieOnline.Data.Domains;

namespace MovieOnline.Repositories
{
    public interface IUserRepository : IRepository<User>
    {
        User FindByEmail(string email);
        bool IsExistEmail(string email);
        bool VerifyUser(string email, string password);
    }

    public class UserRepository : Repository<User>, IUserRepository
    {
        public UserRepository(NeptuneContext context) : base(context)
        {
        }

        public User FindByEmail(string email)
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