import * as React from "react";
import AspectRatio from "@mui/joy/AspectRatio";
import Box from "@mui/joy/Box";
import Button from "@mui/joy/Button";
import Divider from "@mui/joy/Divider";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import FormHelperText from "@mui/joy/FormHelperText";
import Input from "@mui/joy/Input";
import IconButton from "@mui/joy/IconButton";
import Textarea from "@mui/joy/Textarea";
import Stack from "@mui/joy/Stack";
import Select from "@mui/joy/Select";
import Option from "@mui/joy/Option";
import Typography from "@mui/joy/Typography";
import Tabs from "@mui/joy/Tabs";
import TabList from "@mui/joy/TabList";
import Tab, { tabClasses } from "@mui/joy/Tab";
import Breadcrumbs from "@mui/joy/Breadcrumbs";
import Link from "@mui/joy/Link";
import Card from "@mui/joy/Card";
import CardActions from "@mui/joy/CardActions";
import CardOverflow from "@mui/joy/CardOverflow";

import HomeRoundedIcon from "@mui/icons-material/HomeRounded";
import ChevronRightRoundedIcon from "@mui/icons-material/ChevronRightRounded";
import EmailRoundedIcon from "@mui/icons-material/EmailRounded";
import AccessTimeFilledRoundedIcon from "@mui/icons-material/AccessTimeFilledRounded";
import VideocamRoundedIcon from "@mui/icons-material/VideocamRounded";
import InsertDriveFileRoundedIcon from "@mui/icons-material/InsertDriveFileRounded";
import EditRoundedIcon from "@mui/icons-material/EditRounded";

import CountrySelector from "./ContrySelector.jsx";
import { hosts } from "../../const.js";

export default function MyProfile() {
  const [user, setUser] = React.useState(null);

  React.useEffect(() => {
    fetch(`${hosts.backend}/api/myprofile`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("data", data);
        setUser(data);
      })
      .catch((err) => {
        setUser(null);
      });
  }, []);

  const handleChange = (event) => {
    console.log("event", event.target.name, "dfsdf", event.target.value);
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  };

  var profileImgUrl =
    "./img/profiles/" + user?.id + ".jpg" || "./img/profiles/1.jpg";

  const updateProfile = async (event) => {
    event.preventDefault();
    console.log("user new", user);
    try {
      const response = await fetch(`${hosts.backend}/api/myprofile`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify(user),
      });
      console.log("response", response);
      const data = await response.json();
      console.log("data", data);
      setUser(data);
    } catch (err) {
      console.log("err", err);
    }
  };

  return (
    <Box
      sx={{
        flex: 1,
        width: "100%",
      }}
    >
      <Box>
        <Box
          sx={{
            px: {
              xs: 2,
              md: 6,
            },
          }}
        >
          <Typography
            level="h2"
            sx={{
              mt: 1,
              mb: 2,
            }}
          >
            My profile
          </Typography>
        </Box>
        <Tabs
          defaultValue={0}
          sx={{
            bgcolor: "transparent",
          }}
        >
          <TabList
            tabFlex={1}
            size="sm"
            sx={{
              pl: {
                xs: 0,
                md: 4,
              },
              justifyContent: "left",
              [`&& .${tabClasses.root}`]: {
                flex: "initial",
                bgcolor: "transparent",
                [`&.${tabClasses.selected}`]: {
                  fontWeight: "600",
                  "&::after": {
                    height: "2px",
                    bgcolor: "primary.500",
                  },
                },
              },
            }}
          >
            <Tab sx={{ borderRadius: "6px 6px 0 0" }} indicatorInset value={0}>
              Overview
            </Tab>
            <Tab sx={{ borderRadius: "6px 6px 0 0" }} indicatorInset value={1}>
              Dashboard
            </Tab>
          </TabList>
        </Tabs>
      </Box>

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
            <Typography level="body-sm">
              Customize how your profile information will apper to the networks.
            </Typography>
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
                  src={profileImgUrl || "./img/profiles/0.png"}
                  srcSet={profileImgUrl}
                  loading="lazy"
                  alt=""
                />
              </AspectRatio>
              <IconButton
                aria-label="upload new picture"
                size="sm"
                variant="outlined"
                color="neutral"
                sx={{
                  bgcolor: "background.body",
                  position: "absolute",
                  zIndex: 2,
                  borderRadius: "50%",
                  left: 100,
                  top: 170,
                  boxShadow: "sm",
                }}
              >
                <EditRoundedIcon />
              </IconButton>
            </Stack>
            <Stack spacing={2} sx={{ flexGrow: 1 }}>
              <Stack spacing={1}>
                <FormLabel>Name</FormLabel>
                <FormControl
                  sx={{
                    display: {
                      sm: "flex-column",
                      md: "flex-row",
                    },
                    gap: 2,
                  }}
                >
                  <Input
                    size="sm"
                    placeholder="User name"
                    sx={{ flexGrow: 1 }}
                    value={user?.userName}
                    onChange={handleChange}
                    name="userName"
                  />
                  <Input
                    size="sm"
                    placeholder="First name"
                    sx={{ flexGrow: 1 }}
                    value={user?.firstName}
                    onChange={handleChange}
                    name="firstName"
                  />
                  <Input
                    size="sm"
                    placeholder="Last name"
                    sx={{ flexGrow: 1 }}
                    value={user?.lastName}
                    onChange={handleChange}
                    name="lastName"
                  />
                </FormControl>
              </Stack>
              <Stack direction="row" spacing={2}>
                <FormControl>
                  <FormLabel>Language</FormLabel>
                  <Input
                    size="sm"
                    value={user?.language || "English"}
                    onChange={handleChange}
                    name="language"
                  />
                </FormControl>
                <FormControl sx={{ flexGrow: 1 }}>
                  <FormLabel>Email</FormLabel>
                  <Input
                    size="sm"
                    type="email"
                    startDecorator={<EmailRoundedIcon />}
                    placeholder="email"
                    defaultValue="siriwatk@test.com"
                    sx={{ flexGrow: 1 }}
                    value={user?.user?.email || ""}
                    onChange={handleChange}
                    name="email"
                  />
                </FormControl>
              </Stack>
              <div>
                <CountrySelector />
              </div>
            </Stack>
          </Stack>

          <CardOverflow sx={{ borderTop: "1px solid", borderColor: "divider" }}>
            <CardActions sx={{ alignSelf: "flex-end", pt: 2 }}>
              <Button size="sm" variant="outlined" color="neutral">
                Cancel
              </Button>
              <Button size="sm" variant="solid" onClick={updateProfile}>
                Save
              </Button>
            </CardActions>
          </CardOverflow>
        </Card>
        <Card>
          <Box sx={{ mb: 1 }}>
            <Typography level="title-md">Bio</Typography>
            <Typography level="body-sm">
              Write a short introduction to be displayed on your profile
            </Typography>
          </Box>
          <Divider />
          <Stack spacing={2} sx={{ my: 1 }}>
            <Textarea
              size="sm"
              minRows={4}
              sx={{ mt: 1.5 }}
              placeholder="Write a short introduction to be displayed on your profile"
              value={user?.bio || ""}
              onChange={handleChange}
              name="bio"
            />
            <FormHelperText sx={{ mt: 0.75, fontSize: "xs" }}>
              275 characters left
            </FormHelperText>
          </Stack>
          <CardOverflow sx={{ borderTop: "1px solid", borderColor: "divider" }}>
            <CardActions sx={{ alignSelf: "flex-end", pt: 2 }}>
              <Button size="sm" variant="outlined" color="neutral">
                Cancel
              </Button>
              <Button size="sm" variant="solid" onClick={updateProfile}>
                Save
              </Button>
            </CardActions>
          </CardOverflow>
        </Card>
      </Stack>
    </Box>
  );
}
