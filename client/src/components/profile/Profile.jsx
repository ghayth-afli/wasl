import * as React from "react";
import Box from "@mui/joy/Box";
import Typography from "@mui/joy/Typography";
import Tabs from "@mui/joy/Tabs";
import TabList from "@mui/joy/TabList";
import Tab, { tabClasses } from "@mui/joy/Tab";
import TabPanel from "@mui/joy/TabPanel";

import OverviewComponent from "./OverviewComponent.jsx";
import SettingsComponent from "./SettingsComponent.jsx";

export default function MyProfile() {
  const [selectedTab, setSelectedTab] = React.useState(0);
  const switchTab = (event, newValue) => {
    setSelectedTab(newValue);
    console.log(selectedTab);
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
          value={selectedTab}
          onChange={switchTab}
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
            <Tab
              sx={{ borderRadius: "6px 6px 0 0" }}
              indicatorInset
              value={0}
              onClick={() => setSelectedTab(0)}
            >
              Overview
            </Tab>

            <Tab
              sx={{ borderRadius: "6px 6px 0 0" }}
              indicatorInset
              value={1}
              onClick={() => setSelectedTab(1)}
            >
              Settings
            </Tab>
            {/* <Tab
              sx={{ borderRadius: "6px 6px 0 0" }}
              indicatorInset
              value={2}
              onClick={() => setSelectedTab(2)}
            >
              Billing
            </Tab> */}
          </TabList>
          <TabPanel value={0}>
            <OverviewComponent />
          </TabPanel>
          <TabPanel value={1}>
            <SettingsComponent />
          </TabPanel>
          {/* <TabPanel>{selectedTab === 2 && <BillingComponent />}</TabPanel> */}
        </Tabs>
      </Box>
    </Box>
  );
}
