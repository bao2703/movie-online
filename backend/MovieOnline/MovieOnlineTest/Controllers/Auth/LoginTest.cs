using Microsoft.AspNetCore.Mvc;
using Moq;
using MovieOnline.Controllers;
using MovieOnline.Data.Models.Reponses;
using MovieOnline.Data.Models.Requests;
using MovieOnline.Repositories;
using Xunit;

namespace MovieOnlineTest.Controllers.Auth
{
    public class LoginTest
    {
        [Fact]
        public void ShouldResponse200_WhenValidLogin()
        {
            var userRepoMock = new Mock<IUserRepository>();
            var controller = new AuthController(null, null, userRepoMock.Object);

            userRepoMock.Setup(x =>
                    x.VerifyUser(It.IsAny<string>(), It.IsAny<string>()))
                .Returns(true);

            var model = new LoginRequest
            {
                Email = "test@gmail.com",
                Password = "test"
            };

            var result = controller.Login(model);

            Assert.IsType<OkResult>(result);
        }

        [Fact]
        public void ShouldResponse400_WhenInvalidLogin()
        {
            var userRepoMock = new Mock<IUserRepository>();
            var controller = new AuthController(null, null, userRepoMock.Object);

            userRepoMock.Setup(x =>
                    x.VerifyUser(It.IsAny<string>(), It.IsAny<string>()))
                .Returns(false);

            var model = new LoginRequest
            {
                Email = "test@gmail.com",
                Password = "test"
            };

            var result = controller.Login(model);

            var response = Assert.IsType<BadRequestObjectResult>(result);
            Assert.Equal(ErrorReponse.InvalidLogin, response.Value);
        }

        [Fact]
        public void ShouldResponse400_WhenInvalidPayload()
        {
            var controller = new AuthController(null, null, null);

            controller.ModelState.AddModelError(string.Empty, string.Empty);
            var result = controller.Login(new LoginRequest());

            var response = Assert.IsType<BadRequestObjectResult>(result);
            Assert.Equal(ErrorReponse.InvalidPayload, response.Value);
        }
    }
}