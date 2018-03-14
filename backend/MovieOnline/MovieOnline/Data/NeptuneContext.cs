using Microsoft.EntityFrameworkCore;
using MovieOnline.Data.Domains;

namespace MovieOnline.Data
{
    public class NeptuneContext : DbContext
    {
        public NeptuneContext(DbContextOptions<NeptuneContext> options)
            : base(options)
        {
        }

        public DbSet<Movie> Movies { get; set; }

        public DbSet<Genre> Genres { get; set; }

        public DbSet<User> Users { get; set; }
    }
}