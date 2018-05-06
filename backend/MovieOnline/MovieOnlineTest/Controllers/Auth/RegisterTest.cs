using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Moq;
using MovieOnline.Controllers;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;
using Xunit;

namespace MovieOnlineTest.Controllers.Auth
{
    public class RegisterTest
    {
        [Fact]
        public async Task ShouldReponse200_WhenValidRegister()
        {
            var unitOfWorkMock = new Mock<IUnitOfWork>();
            var userRepoMock = new Mock<IUserRepository>();
            var controller = new AuthController(AutoMapperConfig.Instance, unitOfWorkMock.Object, userRepoMock.Object);

            userRepoMock.Setup(x =>
                    x.IsExistEmail(It.IsAny<string>()))
                .Returns(false);

            var model = new RegisterRequest
            {
                Name = "test",
                Email = "test@gmail.com",
                Password = "test"
            };

            var result = await controller.Register(model);

            Assert.IsType<OkResult>(result);
        }

        [Fact]
        public async Task ShouldResponse400_WhenConflictEmail()
        {
            var userRepoMock = new Mock<IUserRepository>();
            var controller = new AuthController(AutoMapperConfig.Instance, null, userRepoMock.Object);

            userRepoMock.Setup(x =>
                    x.IsExistEmail(It.IsAny<string>()))
                .Returns(true);

            var model = new RegisterRequest
            {
                Name = "test",
                Email = "test@gmail.com",
                Password = "test"
            };

            var result = await controller.Register(model);

            var response = Assert.IsType<BadRequestObjectResult>(result);
            Assert.Equal(ErrorResponse.EmailConflict, response.Value);
        }

        [Fact]
        public async Task ShouldResponse400_WhenInvalidPayload()
        {
            var controller = new AuthController(null, null, null);

            controller.ModelState.AddModelError(string.Empty, string.Empty);
            var result = await controller.Register(new RegisterRequest());

            var response = Assert.IsType<BadRequestObjectResult>(result);
            Assert.Equal(ErrorResponse.InvalidPayload, response.Value);
        }
    }
}