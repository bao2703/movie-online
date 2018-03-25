using System.ComponentModel.DataAnnotations;
using AutoMapper;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Requests
{
    public class RegisterRequest
    {
        [Required]
        public string Name { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        public string Password { get; set; }
    }

    public class LoginRequest
    {
        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        public string Password { get; set; }
    }

    public class AuthRequestMapperProfile : Profile
    {
        public AuthRequestMapperProfile()
        {
            CreateMap<RegisterRequest, UserEntity>();
            CreateMap<LoginRequest, UserEntity>();
        }
    }
}