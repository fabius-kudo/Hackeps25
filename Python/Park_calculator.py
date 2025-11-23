import  Neighbourhood_fetcher
import Park_finder
import json
import os

# Base directory of this script
base_dir = os.path.dirname(os.path.abspath(__file__))

# JSON file path
nh_file = os.path.join(base_dir, "JSON", "la_neighborhoods_raw.json")

# Output JSON file path (constant name, always overwritten)
output_file = os.path.join(base_dir, "JSON", "parks.json")

def main():
    Neighbourhood_fetcher.main()  # runs the main function from Neighbourhood_fetcher.py

    nh_count = Neighbourhood_fetcher.get_nh_count()

    with open(nh_file, "r") as f:
        neighbourhoods = json.load(f)

    results = []  # store results here

    for i in range(nh_count - 1):
        nh = neighbourhoods[i]
        #read one item from json and get its lat and long

        lat = nh.get("lat")
        lon = nh.get("lon")
        name = nh.get("tags", {}).get("name", "Unknown")

        parks = Park_finder.get_parks_near(lat, lon, radius=1000)                #find parks near given i

        park_count = len(parks)

        #add to json file
        results.append({
            "name": name,
            "parks": park_count
        })

        print(f"found {park_count} parks near {name}\n")



    # Save results to JSON (overwrite old file)
    with open(output_file, "w") as out:
        json.dump(results, out, indent=2)

if __name__ == "__main__":
    main()