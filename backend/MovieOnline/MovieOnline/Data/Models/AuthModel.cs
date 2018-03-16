using System.ComponentModel.DataAnnotations;
using AutoMapper;
using MovieOnline.Data.Domains;

namespace MovieOnline.Data.Models
{
    public class RegisterModel
    {
        [Required] public string Name { get; set; }

        [Required] public string Email { get; set; }

        [Required] public string Password { get; set; }
    }

    public class LoginModel
    {
        [Required] public string Email { get; set; }

        [Required] public string Password { get; set; }
    }

    public class AuthMapperProfile : Profile
    {
        public AuthMapperProfile()
        {
            CreateMap<RegisterModel, User>();
            CreateMap<LoginModel, User>();
        }
    }
}