using AutoMapper;
using Microsoft.Extensions.DependencyInjection;
using MovieOnline.Data.Seeders;
using MovieOnline.Repositories;

namespace MovieOnline.Extensions
{
    public static class ServiceCollectionExtensions
    {
        public static void RegisterApplicationServices(this IServiceCollection services)
        {
            services.AddAutoMapper();
            services.AddTransient<Seeder>();
            services.AddTransient<IUnitOfWork, UnitOfWork>();
            services.AddTransient<IUserRepository, UserRepository>();
            services.AddTransient<IMovieRepository, MovieRepository>();
            services.AddTransient<IGenreRepository, GenreRepository>();
        }
    }
}