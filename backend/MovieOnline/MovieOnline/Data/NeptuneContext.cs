using Microsoft.EntityFrameworkCore;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data
{
    public class NeptuneContext : DbContext
    {
        public NeptuneContext(DbContextOptions<NeptuneContext> options)
            : base(options)
        {
        }

        public DbSet<MovieEntity> Movies { get; set; }

        public DbSet<EpisodeEntity> Episodes { get; set; }

        public DbSet<GenreEntity> Genres { get; set; }

        public DbSet<CommentEntity> Comments { get; set; }

        public DbSet<UserEntity> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<GenreMovieEntity>().HasKey(x => new { x.MovieId, x.GenreId });

            modelBuilder.Entity<GenreMovieEntity>()
                .HasOne(x => x.Movie)
                .WithMany(x => x.GenreMovies)
                .HasForeignKey(x => x.MovieId);

            modelBuilder.Entity<GenreMovieEntity>()
                .HasOne(x => x.Genre)
                .WithMany(x => x.GenreMovies)
                .HasForeignKey(x => x.GenreId);

            modelBuilder.Entity<LikeEntity>().HasKey(x => new { x.MovieId, x.UserId });

            modelBuilder.Entity<LikeEntity>()
                .HasOne(x => x.Movie)
                .WithMany(x => x.Likes)
                .HasForeignKey(x => x.MovieId);

            modelBuilder.Entity<LikeEntity>()
                .HasOne(x => x.User)
                .WithMany(x => x.Likes)
                .HasForeignKey(x => x.UserId);
        }
    }
}