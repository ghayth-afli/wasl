import * as React from "react";

import MyProfile from "../../components/profile/Profile";
import Navbar from "../../components/navbar/Navbar";

export default function Profile() {
  return (
    <>
      <Navbar></Navbar>
      <MyProfile></MyProfile>
    </>
  );
}
