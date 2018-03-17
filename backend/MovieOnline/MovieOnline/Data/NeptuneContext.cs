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

        public DbSet<Episode> Episodes { get; set; }

        public DbSet<Genre> Genres { get; set; }

        public DbSet<Comment> Comments { get; set; }

        public DbSet<User> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<GenreMovie>().HasKey(x => new { x.MovieId, x.GenreId });

            modelBuilder.Entity<GenreMovie>()
                .HasOne(x => x.Movie)
                .WithMany(x => x.GenreMovies)
                .HasForeignKey(x => x.MovieId);

            modelBuilder.Entity<GenreMovie>()
                .HasOne(x => x.Genre)
                .WithMany(x => x.GenreMovies)
                .HasForeignKey(x => x.GenreId);

            modelBuilder.Entity<Like>().HasKey(x => new { x.MovieId, x.UserId });

            modelBuilder.Entity<Like>()
                .HasOne(x => x.Movie)
                .WithMany(x => x.Likes)
                .HasForeignKey(x => x.MovieId);

            modelBuilder.Entity<Like>()
                .HasOne(x => x.User)
                .WithMany(x => x.Likes)
                .HasForeignKey(x => x.UserId);
        }
    }
}