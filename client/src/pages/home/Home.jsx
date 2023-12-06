import React from "react";
import ReactDOM from "react-dom";
import "./Home.scss";
import Featured from "../../components/featured/Featured";
import TrustedBy from "../../components/trustedBy/TrustedBy";
import Slide from "../../components/slide/Slide";
import CatCard from "../../components/catCard/CatCard";
import ProjectCard from "../../components/projectCard/ProjectCard";
import { cards, projects } from "../../data";
import Testimonials from "../../components/testimonials/Testimonials";
import { ChakraProvider } from "@chakra-ui/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
{
  /* Font Awesome CDN*/
}

function Home() {
  return (
    <div className="home">
      <Featured />
      {/* <TrustedBy />
      <Slide slidesToShow={5} arrowsScroll={5}>
        {cards.map((card) => (
          <CatCard key={card.id} card={card} />
        ))}
      </Slide> */}
      {/* about section */}
      <section>
        <div className="row">
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                {/* <img src="./img/bars-staggered-solid.png" alt="" /> */}
              </div>
              <h3>Transparent Offers</h3>
              <p>
                Browse through transparent offers posted by travelers, including
                details on destinations, delivery timelines, and pricing.
              </p>
            </div>
          </div>
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                <FontAwesomeIcon icon="fa-solid fa-bars" />
              </div>
              <h3>Trusted Travelers</h3>
              <p>
                Connect with a community of reliable and vetted travelers who
                are ready to assist you in delivering items to your loved ones
              </p>
            </div>
          </div>
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                <FontAwesomeIcon icon="fa-solid fa-bars" />
              </div>
              <h3>Personalized Requests</h3>
              <p>
                Can't find the right offer? No problem. Submit a personalized
                request, and our platform will match you with a traveler who can
                fulfill your specific requirements.
              </p>
            </div>
          </div>
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                <FontAwesomeIcon icon="fa-solid fa-bars" />
              </div>
              <h3>Secure Payments</h3>
              <p>
                We prioritize the security of your transactions. Our platform
                integrates advanced payment systems to protect your financial
                information.
              </p>
            </div>
          </div>
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                <FontAwesomeIcon icon="fa-solid fa-bars" />
              </div>
              <h3>Instant Communication</h3>
              <p>
                communicate with your chosen traveler through our integrated
                chat feature. This real-time messaging system allows you to
                discuss details, ask questions, and stay updated on the progress
                of your shipment.
              </p>
            </div>
          </div>
          <div className="column">
            <div className="card">
              <div className="icon-wrapper">
                <FontAwesomeIcon icon="fa-solid fa-bars" />
              </div>
              <h3>Customer Support</h3>
              <p>
                Have questions or need assistance? Our dedicated customer
                support team is here to help. Reach out to us at any time, and
                we'll ensure that your experience with Wasl is smooth and
                enjoyable.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* end about section */}
      {/* about section */}

      <div className="features">
        <div className="container">
          <div className="item ">
            <h1>A whole world is closer to you today at your fingertips</h1>
            <div className="title">
              {/* <img src="./img/check.png" alt="" /> */}
              The best for every budget
            </div>
            <p>
              Welcome to Wasl, where distance is just a word. We understand the
              importance of staying connected with your loved ones, even when
              they're miles away. Our platform is designed to make sending and
              receiving items across borders a seamless and secure experience.
            </p>
            <div className="title">
              {/* <img src="./img/check.png" alt="" /> */}
              Our Mession
            </div>
            <p>
              At Wasl, our mission is to bridge the gap between distances. We
              believe in creating a global community where users can easily find
              solutions to send meaningful items to their relatives or friends
              living abroad. Whether it's a care package, a special gift, or
              essential items, we've got you covered.
            </p>
            <div className="title">
              {/* <img src="./img/check.png" alt="" /> */}
              How it Works
            </div>
            <p>
              Explore our platform to discover a network of trusted travelers
              ready to assist you. Simply browse through offers posted by
              travelers or request a personalized delivery. Our secure system
              ensures that your transactions are protected, providing you with
              peace of mind.
            </p>
          </div>
          <div className="item">
            <img src="./img/poster-2.png" controls />
          </div>
        </div>
      </div>
      {/* end of about section */}
      {/* <div className="explore">
        <div className="container">
          <h1>Explore the marketplace</h1>
          <div className="items">
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/graphics-design.d32a2f8.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Graphics & Design</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/online-marketing.74e221b.svg"
                alt=""
              />
              <div className="line"></div>

              <span>Digital Marketing</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/writing-translation.32ebe2e.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Writing & Translation</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/video-animation.f0d9d71.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Video & Animation</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/music-audio.320af20.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Music & Audio</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/programming.9362366.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Programming & Tech</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/business.bbdf319.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Business</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/lifestyle.745b575.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Lifestyle</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/data.718910f.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Data</span>
            </div>
            <div className="item">
              <img
                src="https://fiverr-res.cloudinary.com/npm-assets/@fiverr/logged_out_homepage_perseus/apps/photography.01cf943.svg"
                alt=""
              />
              <div className="line"></div>
              <span>Photography</span>
            </div>
          </div>
        </div>
      </div> */}
      <div className="features dark">
        <div className="container">
          <div className="item">
            <h1>
              Wasl <i>business</i>
            </h1>
            <h1>
              A business solution designed for <i>you</i>
            </h1>
            <p>
              Upgrade to a curated experience packed with tools and benefits,
              dedicated to businesses
            </p>
            <div className="title">
              <img src="./img/check.png" alt="" />
              Connect to travelers with proven travel experience
            </div>

            <div className="title">
              <img src="./img/check.png" alt="" />
              Get matched with the perfect clients by a customer success manager
            </div>

            <div className="title">
              <img src="./img/check.png" alt="" />
              Manage teamwork and boost productivity with one powerful workspace
            </div>
            <button>Explore Wasl Business</button>
          </div>
          <div className="item">
            <img
              src="https://images.pexels.com/photos/6868618/pexels-photo-6868618.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
              alt=""
            />
            {/* <img className="imgCustom" src="./img/poster.png" alt="" /> */}
          </div>
        </div>
      </div>
      <ChakraProvider>
        {" "}
        <Testimonials />
      </ChakraProvider>

      {/* <Slide slidesToShow={4} arrowsScroll={4}>
        {projects.map((card) => (
          <ProjectCard key={card.id} card={card} />
        ))}
      </Slide> */}
    </div>
  );
}

export default Home;
