import * as React from "react";
import AspectRatio from "@mui/joy/AspectRatio";
import Box from "@mui/joy/Box";
import Divider from "@mui/joy/Divider";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Stack from "@mui/joy/Stack";
import Typography from "@mui/joy/Typography";
import Card from "@mui/joy/Card";

export default function SettingsComponent() {
  const userData = {
    name: "Siriwat Knp",
    role: "UI Developer",
    email: "jhon@doe.com",
    country: "Thailand",
    timezone: "Indochina Time (Bangkok)",
  };
  return (
    <>
      <Stack
        spacing={4}
        sx={{
          display: "flex",
          maxWidth: "800px",
          mx: "auto",
          px: {
            xs: 2,
            md: 6,
          },
          py: {
            xs: 2,
            md: 3,
          },
        }}
      >
        <Card>
          <Box sx={{ mb: 1 }}>
            <Typography level="title-md">Personal info</Typography>
          </Box>
          <Divider />
          <Stack
            direction="row"
            spacing={3}
            sx={{ display: { xs: "none", md: "flex" }, my: 1 }}
          >
            <Stack direction="column" spacing={1}>
              <AspectRatio
                ratio="1"
                maxHeight={200}
                sx={{ flex: 1, minWidth: 120, borderRadius: "100%" }}
              >
                <img
                  src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=286"
                  srcSet="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=286&dpr=2 2x"
                  loading="lazy"
                  alt=""
                />
              </AspectRatio>
            </Stack>
            <Stack spacing={2} sx={{ flexGrow: 1 }}>
              <Stack spacing={1}>
                <FormLabel>Name</FormLabel>
                <Typography level="h5">{userData.name}</Typography>
              </Stack>
              <Stack direction="row" spacing={8}>
                <FormControl>
                  <FormLabel>Role</FormLabel>
                  <Typography level="h5">{userData.role}</Typography>
                </FormControl>
                <FormControl sx={{ flexGrow: 1 }}>
                  <FormLabel>Email</FormLabel>
                  <Typography level="h5">{userData.email}</Typography>
                </FormControl>
              </Stack>
              <div>
                <FormControl>
                  <FormLabel>Country</FormLabel>
                  <Typography level="h5">{userData.country}</Typography>
                </FormControl>
              </div>
              <div>
                <FormControl sx={{ display: { sm: "contents" } }}>
                  <FormLabel>Timezone</FormLabel>
                  <Typography level="h5">{userData.timezone}</Typography>
                </FormControl>
              </div>
            </Stack>
          </Stack>
        </Card>
        <Card>
          <Box sx={{ mb: 1 }}>
            <Typography level="title-md">Your Bio</Typography>
            <Typography level="body-sm">
              {`Hi, I'm ${userData.name}. I'm a UI Developer from Thailand. I
                enjoy building everything from small business sites to rich
                interactive web apps. If you are a business seeking a web
                presence or an employer looking to hire, you can get in touch
                with me here.`}
            </Typography>
          </Box>
        </Card>
      </Stack>
    </>
  );
}
