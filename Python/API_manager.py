import requests
import json
import os

def fetch_overpass(query):

    url = "https://overpass-api.de/api/interpreter"
    response = requests.get(url, params={"data": query})
    response.raise_for_status()
    return response.json()


#JSON folder
output_dir = "JSON"
os.makedirs(output_dir, exist_ok=True)

# Save JSON to file
def save_elements(data, filename):
    filepath = os.path.join(output_dir, filename)
    with open(filepath, "w") as f:
        json.dump(data, f, indent=2)
    print(f"Saved {len(data)} items to {filepath}")

def print_first_n(elements, n):
    print(f"First {n} elements:")
    for i, el in enumerate(elements[:n], 1):
        print(f"{i}: {json.dumps(el, indent=2)}\n")


def main():
    key = input("Enter OSM key (e.g., amenity, shop, leisure): ").strip()
    value = input(f"Enter value for '{key}' (e.g., cafe, park): ").strip()

    # Los Angeles bounding box (south, west, north, east)
    bbox = "34.00,-118.50,34.30,-118.10"

    query = f"""
    [out:json];
    (
      node["{key}"="{value}"]({bbox});
      way["{key}"="{value}"]({bbox});
      relation["{key}"="{value}"]({bbox});
    );
    out center;
    """

    print("Fetching data from Overpass...")
    data = fetch_overpass(query)

    # Keep all raw elements
    elements = data.get("elements", [])

    # Save raw elements to JSON
    filename = f"{key}_{value}_raw.json"
    save_elements(elements, filename)

    # Print first 5 elements for inspection
    print_first_n(elements, n=5)

if __name__ == "__main__":
    main()