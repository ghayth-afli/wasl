import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import { Link, useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { hosts } from "../../const.js";
import "./Navbar.scss";

function Navbar() {
  const navigate = useNavigate();
  const [active, setActive] = useState(false);
  const [open, setOpen] = useState(false);
  const { pathname } = useLocation();
  const [user, setUser] = useState(null);
  var currentUser;

  const token = localStorage.getItem("token") || null;

  const isActive = () => {
    window.scrollY > 0 ? setActive(true) : setActive(false);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("gig");
    navigate("/");
  };

  useEffect(() => {
    window.addEventListener("scroll", isActive);
    return () => {
      window.removeEventListener("scroll", isActive);
    };
  }, []);

  function getUserData() {
    fetch(`${hosts.backend}/api/myprofile`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token") || null,
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setUser(data);
      })
      .catch((err) => {
        setUser(null);
        currentUser = null;
      });
  }
  function switchRole() {
    fetch(`${hosts.backend}/api/switch`, {
      method: "PUT",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        getUserData();
      })
      .catch((err) => {
        console.log("err", err);
        // setUser(null);
      });
  }

  useEffect(() => {
    getUserData();
  }, []);

  if (token) {
    currentUser = user;
  } else {
    currentUser = null;
    navigate("/login");
  }


  return (
    <div className={active || pathname !== "/" ? "navbar active" : "navbar"}>
      <div className="container">
        <div className="logo">
          <Link className="link" to="/">
            <span className="text"><img src="https://res.cloudinary.com/dlxlpazb7/image/upload/v1700930807/wasl/wasl-logo_i6trco.png" alt="" width="350px"/></span>
          </Link>
          {/* <span className="dot">.</span> */}
        </div>
        <div className="links">
          <Link className="link" to="/gigs">
            <span>Explore</span>
          </Link>
          {currentUser && !currentUser?.traveler && (
            <Button onClick={switchRole}>Become a Traveller</Button>
          )}
          {currentUser?.traveler && (
            <Button onClick={switchRole}>Become a Sender</Button>
          )}
          {currentUser ? (
            <div className="user" onClick={() => setOpen(!open)}>
              <img src={user?.image ||  "https://res.cloudinary.com/dlxlpazb7/image/upload/v1700916977/wasl/noavatar_xvf6ez.png" } alt="user image" />
              <span>{currentUser?.firstName + " " + currentUser?.lastName}</span>
              {open && (
                <div className="options">
                  <Link className="link" to="/profile">
                    Profile
                  </Link>
                  {currentUser.traveler && (
                    <>
                      <Link className="link" to="/mygigs">
                        Gigs
                      </Link>
                      <Link className="link" to="/add">
                        Add New Gig
                      </Link>
                    </>
                  )}
                  <Link className="link" to="/orders">
                    Orders
                  </Link>
                  <Link className="link" to="/messages">
                    Messages
                  </Link>
                  <Link className="link" onClick={handleLogout}>
                    Logout
                  </Link>
                </div>
              )}
            </div>
          ) : (
            <>
              <Link className="link" to="/login">
                <button>Sign in</button>
              </Link>
              <Link className="link" to="/register">
                <button>Join</button>
              </Link>
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default Navbar;
